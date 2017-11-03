(ns martian-dice.events
  (:require [re-frame.core :as rf]
            [martian-dice.db :as db]
            [martian-dice.game.game :as game]))

(rf/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))

(rf/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc-in db [:view-state :active-panel] active-panel)))

(rf/reg-event-db
 ::select-dice
 (fn [{:keys [game] :as db} [_ dice-type]] 
   (db/update-game db #(game/select-dice game dice-type))))

;; (rf/reg-event-db
;;  ::end-round
;;  (fn [db _]
;;    (assoc db :game (game/end-round (:game db)))))

(rf/reg-event-db
 ::roll-dice
 (fn [db _] (db/update-game db game/roll-dice)))



(rf/reg-event-db
 ::start-new-game
 (fn [db _]
   (-> db
       (assoc-in [:view-state :active-panel] :game-panel)
       (assoc :game game/new-game))))
