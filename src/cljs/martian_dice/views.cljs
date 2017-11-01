(ns martian-dice.views
  (:require [martian-dice.subs :as subs]
            [martian-dice.events :as events]
            [re-frame.core :as rf]))


;; home

(defn home-panel []
  [:div (str "Hello from " ". This is the home Page.")
   [:div [:a {:href "#/new"} "Start new game"]]])

;; about

(defn players-component []
  (let [players (rf/subscribe [::subs/players])]
    (.log js/console @players)
    [:div
     [:h2 "Players"]
     [:ul
      (for [player @players]
        [:dl {:key (:id player)}
         [:dt "Name:"]
         [:dd (:name player)]
         [:dt "Score"]
         [:dd (:score player)]])]]))

(defn roll-dice-component []
  [:div
   [:button {:on-click #(rf/dispatch [::events/roll-dice])}  "Roll dice"]])

(defn last-roll-component []
  (let [last-roll (rf/subscribe [::subs/latest-roll])]
    [:div.last-roll
     [:ul
      (for [result @last-roll]
        (str (first result)
             (second result)))]]))

(defn game-panel []
  [:div "This is the Game Page."
   (players-component)
   (last-roll-component)
   (roll-dice-component)
   ])

;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :game-panel [game-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (rf/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))

