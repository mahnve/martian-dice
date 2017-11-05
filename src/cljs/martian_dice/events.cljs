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
   (db/with-view db active-panel)))

(rf/reg-event-db
 ::select-dice
 (fn [{:keys [game] :as db} [_ dice-type]] 
   (db/with-game db #(game/select-dice game dice-type))))

(rf/reg-event-db
 ::end-turn
 (fn [db _]
   (db/with-game db game/end-round)))

(rf/reg-event-db
 ::roll-dice
 (fn [db _] (db/with-game db game/roll-dice)))

(rf/reg-event-db
 ::new-game
 (fn [db _] (db/update-game db game/new-game)))

(rf/reg-event-db
 ::start-new-game
 (fn [db _]
   (-> db
       (db/with-view :game-panel)
       (db/with-game game/new-game))))
