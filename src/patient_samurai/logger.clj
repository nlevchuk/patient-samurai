(ns patient-samurai.logger)

(def write-log println)

(defn info [& args] (apply write-log args))

(defn warn [& args] (apply write-log args))

(defn error [& args] (apply write-log args))

(comment
  (info "Hello")
  (info "Hello" "World"))
