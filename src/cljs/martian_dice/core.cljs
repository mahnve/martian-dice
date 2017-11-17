(ns martian-dice.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [martian-dice.events :as events]
            [martian-dice.routes :as routes]
            [stylefy.core :as stylefy]
            [martian-dice.views :as views]
            [martian-dice.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (js/console.log "init")
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (stylefy/init)
  (dev-setup)
  (mount-root))
