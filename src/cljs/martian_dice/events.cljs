(ns martian-dice.events
  (:require [re-frame.core :as rf]
            [martian-dice.db :as db]
            [martian-dice.game :as game]))

(rf/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))

(rf/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc-in db [:view-state :active-panel] active-panel)))

(rf/reg-event-db
 ::roll-dice
 (fn [db _]
   (assoc-in db
             [:game :latest-roll]
             (game/roll-dice 15))))

(rf/reg-event-db
 ::start-new-game
 (fn [db _]
   (-> db 
       (assoc-in [:view-state :active-panel] :game-panel)
       (assoc :game db/new-game))))
