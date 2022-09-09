(ns patient-samurai.db
  (:require
    [next.jdbc :as jdbc]))

(def ^:private db-url
  (System/getenv "PATIENT_SAMURAI_DB_URL"))

(def datasource (jdbc/get-datasource db-url))

(comment
  (jdbc/get-connection datasource)
  (jdbc/execute! datasource ["select * from patients"]))
