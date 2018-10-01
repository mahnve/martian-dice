(ns martian-dice.subs
  (:require [re-frame.core :as rf]
            [martian-dice.game.game :as game]
            [taoensso.timbre :as log]))


(rf/reg-sub
 ::active-panel
 (fn [db _]
   (get-in db [:view-state :active-panel] )))

(rf/reg-sub
 ::selected-dice
 (fn [{:keys [:game]} _]
   (game/selected-dice game)))

(rf/reg-sub
 ::saved-dice
 (fn [{:keys [:game]} _]
   (game/saved-dice game)))

(rf/reg-sub
 ::players
 (fn [db _]
   (get-in db [:game ::game/players])))

(rf/reg-sub
 ::latest-roll
 (fn [{:keys [:game]} _]
   (game/latest-roll game)))
