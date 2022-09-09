(ns patient-samurai.migrations.create
  (:require
    [patient-samurai.migrations.core :as core]
    [patient-samurai.logger :as logger]
    [clojure.java.io :as io]))

(defn- compose-migration-name [timestamp name]
  (str timestamp "-" name ".sql"))

(defn- compose-migration-path [name]
  (str (io/resource core/migrations) "/" name))

(defn- create-migration-file [path] (spit path ""))

(defn -main [& args]
  (let [name (first (or args '()))]
    (if (some? name)
      (let [timestamp (core/generate-timestamp)
            fullname (compose-migration-name timestamp name)
            path (compose-migration-path fullname)]
        (create-migration-file path)
        (logger/info "Migration created -" fullname))
      (logger/info "Specify migration name"))))

(comment
  (-main "create-table"))
