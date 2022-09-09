(ns patient-samurai.migrations.migrate
  (:require
    [patient-samurai.migrations.core :as core]
    [patient-samurai.db :as db]
    [patient-samurai.logger :as logger]
    [clojure.java.io :as io]
    [clojure.set :as set]
    [next.jdbc :as jdbc])
  (:import
    java.util.Date
    java.text.SimpleDateFormat))

(def meta-table-name "migrations_meta")

(defn- create-meta-table-if-not-exists []
  (jdbc/execute-one! db/datasource
    [(str "CREATE TABLE IF NOT EXISTS " meta-table-name " (name varchar(255) PRIMARY KEY)")]))

(defn- add-migration-to-meta-table [tx migration]
  (jdbc/execute-one! tx
    [(str "INSERT INTO " meta-table-name " (name) VALUES ('" migration "')")]))

(defn- fetch-migrations []
  (jdbc/execute! db/datasource
    [(str "Select * from " meta-table-name)]))

(defn- get-sql-filenames []
  (for [name (vec (.list (io/file (io/resource core/migrations))))
      :let [sql-file? (re-find (re-matcher #".sql$" name))]
      :when sql-file?]
    name))

(defn- apply-migration [name]
  (do
    (logger/info "Apply migration -" name)
    (let [sql (slurp (io/resource (str core/migrations "/" name)))]
      (jdbc/with-transaction [tx db/datasource]
        (jdbc/execute-one! tx [sql])
        (add-migration-to-meta-table tx name)))
    (logger/info "Complete migration -" name)))

(defn- map-values->strings [coll]
  (map #(apply val %) coll))

(defn- run-migrations []
  (let [sql-files (get-sql-filenames)]
    (if (empty? sql-files)
      (logger/info "No migrations found")
      (let [added-migrations (fetch-migrations)
            new-files (set/difference
                        (set sql-files)
                        (set (map-values->strings added-migrations)))]
        (if (empty? new-files)
          (logger/info "Migrations are up to date")
          (let [sorted-files (apply sorted-set new-files)]
            (doseq [name sorted-files]
              (apply-migration name))))))))

(defn -main [& args]
  (do
    (create-meta-table-if-not-exists)
    (run-migrations)))

(comment
  (-main))
