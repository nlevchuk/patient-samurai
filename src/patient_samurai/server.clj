(ns patient-samurai.server
  (:require
    [compojure.core :as compojure]
    [compojure.route :as route]
    [rum.core :as rum]
    [ring.adapter.jetty :refer [run-jetty]]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:gen-class))

(defonce patients
  (atom [{:id 1
          :fname "Roman"
          :mname "Andreevich"
          :lname "Suteev"
          :gender "Male"
          :birth-date "2000-01-03"
          :address {:country "Montenegro"
                    :city "Budva"
                    :street "Jadranski Put"
                    :number 11}
          :insurance "GH312DS"}
         {:id 2
          :fname "Olga"
          :mname "Fedorovna"
          :lname "Dostoevskaya"
          :gender "Female"
          :birth-date "1995-05-24"
          :address {:country "Montenegro"
                    :city "Tivat"
                    :street "21 Novembra"
                    :number 44}
          :insurance "ELV5KV"}]))

(defn format-address [{:keys [country, city, street, number]}]
  (str country ", " city ", " street " " number))

(rum/defc patient-row [patient]
  (let [{:keys [id fname mname lname gender birth-date address insurance]} patient]
    [:tr {:key id}
      [:td (str fname " " mname " " lname)]
      [:td gender]
      [:td birth-date]
      [:td (format-address address)]
      [:td insurance]]))

(rum/defc patient-list [patients]
  [:<>
   [:header
    [:h1 "Patient List"]]
   [:main
    [:table
      [:thead
       [:tr
        [:th "Full Name"]
        [:th "Gender"]
        [:th "Birth Date"]
        [:th "Address"]
        [:th "Insurance Number"]]]
      [:tbody
        (for [patient patients]
         (patient-row patient))]]]])

(rum/defc index []
  [:html {:lang "en"}
   [:head
    [:title "Patient Samurai"]
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1"}]
    [:link {:href "styles.css"
            :rel "stylesheet"
            :type "text/css"}]]
   [:body
    [:div#app
     (patient-list @patients)]
    [:script {:src "main.js" :type "text/javascript" }]]])

(compojure/defroutes routes
  (compojure/GET "/" [] (str "<!DOCTYPE html>" (rum/render-html (index))))
  ; (compojure/GET "/new" [] ...)
  ; (compojure/GET "/edit/:id" [] ...)
  ; (compojure/POST "/edit/:id" [] ...)
  ; (compojure/DELETE "/:id" [] ...)
  (route/not-found "Not Found"))

(defn -main [& args]
  (run-jetty
    (wrap-defaults #'routes site-defaults)
    {:port 3000
     :join? false}))

(comment
  (def server (-main))
  (.stop server))
