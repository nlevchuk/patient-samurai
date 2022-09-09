(ns patient-samurai.migrations.core
  (:import
    java.util.Date
    java.text.SimpleDateFormat))

(def migrations "migrations")

(defn- now [] (Date.))
(defn generate-timestamp []
  (.format (SimpleDateFormat. "YYYYMMddHHmmss") (now)))
