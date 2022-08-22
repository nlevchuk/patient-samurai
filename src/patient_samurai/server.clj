(ns patient-samurai.server
  (:require
      [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn handler [req]
  {:status 200
   :headers {"Content-Type" "text/plain; charset=UTF-8"}
   :body (str "Hello World! \n (uri = " (get req :uri) ")")})

(defn -main [& args]
  (run-jetty #'handler {:port 3000
                        :join? false}))

(comment
  (def server (-main))
  (.stop server))
