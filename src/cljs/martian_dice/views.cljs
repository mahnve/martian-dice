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
    [:div
     [:h2 "Players"]
     [:ul
      (for [{:keys [:name :score]} @players]
        [:li{:key name}
         [:label "Name:"]
         [:p name]
         [:label "Score"]
         [:p score]])]]))

(defn roll-dice-component []
  [:div
   [:button {:on-click #(rf/dispatch [::events/roll-dice])}  "Roll dice"]])

(defn end-round-component []
  [:div
   [:button {:on-click #(rf/dispatch [::events/end-round])} "End Round"]])

(defn saved-dice-component []
  (let [saved-dice @(rf/subscribe [::subs/saved-dice])]
    [:ul
     (for [[dice no-of-dice] saved-dice]
       [:li 
        [:p  dice]
        [:p no-of-dice]])]))

(defn is-selected? [die selected-dice]
  (if (some #{die} selected-dice)
    "selected"
    "unselected"))

(defn last-roll-component []
  (let [last-roll @(rf/subscribe [::subs/latest-roll])
        selected-dice @(rf/subscribe [::subs/selected-dice])]
    [:div.last-roll
     [:ul
      (for [[dice no-of-dice] last-roll]
        [:li {:on-click #(rf/dispatch [::events/select-dice dice])
              :class (is-selected? dice selected-dice)
              :key (str dice)}
         [:p  dice]
         [:p no-of-dice]])]]))

(defn game-panel []
  [:div "This is the Game Page."
   (players-component)
   (last-roll-component)
   (roll-dice-component)
   (end-round-component)
   (saved-dice-component)
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

