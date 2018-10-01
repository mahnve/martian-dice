(ns martian-dice.views
  (:require [martian-dice.subs :as subs]
            [martian-dice.events :as events]
            [re-frame.core :as rf]
            [stylefy.core :as s]))

;;home

(defn home-panel []
  [:div (str "Hello from " ". This is the home Page.")
   [:div [:a {:href "#/new"} "Start new game"]]])


(defn players-component []
  (let [players (rf/subscribe [::subs/players])]
    [:div.row
     [:h2 "Players"]
     [:ul.column
      (for [{:keys [:name :score]} @players]
        [:li{:key name}
         [:label "Name:"]
         [:p name]
         [:label "Score"]
         [:p score]])]]))

(defn roll-dice-component []
  [:div
   [:button {:on-click #(rf/dispatch [::events/roll-dice])}  "Roll dice"]])

(defn new-game-component []
  [:div
   [:button {:on-click #(rf/dispatch [::events/new-game])} "New game"]])

(defn end-turn-component []
  [:div
   [:button {:on-click #(rf/dispatch [::events/end-turn])} "End Turn"]])

(defn saved-dice-component []
  (let [saved-dice @(rf/subscribe [::subs/saved-dice])]
    [:ul
     (for [[dice no-of-dice] saved-dice]
       ^{:key (str dice)} [:li
                           [:p dice]
                           [:p no-of-dice]])]))

(defn is-selected [die selected-dice]
  (if (some #{die} selected-dice)
    {:background-color "#Dcdcdc"}
    {:background-color "#FFFFFF"}))

(defn last-roll-component []
  (let [last-roll @(rf/subscribe [::subs/latest-roll])
        selected-dice @(rf/subscribe [::subs/selected-dice])]
    [:div.last-roll
     [:ul
      (for [[dice no-of-dice] last-roll]
        [:li (merge (s/use-style (is-selected dice selected-dice))
                    {:on-click #(rf/dispatch [::events/select-dice dice])
                     :key (str dice)})
         [:p  dice]
         [:p no-of-dice]])]]))

(def grid-wrap {:display "grid"
                :grid-gap "20px"})

(defn game-panel []
  [:div (s/use-style grid-wrap) 
   (players-component)
   (last-roll-component)
   (roll-dice-component)
   (saved-dice-component)
   (end-turn-component)
   (new-game-component)
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
