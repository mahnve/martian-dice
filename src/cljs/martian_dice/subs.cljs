(ns martian-dice.subs
  (:require [re-frame.core :as rf]))


(rf/reg-sub
 ::active-panel
 (fn [db _]
   (get-in db [:view-state :active-panel] )))

(rf/reg-sub
 ::selected-dice
 (fn [db _]
   (get-in db [:game :selected-dice])))

(rf/reg-sub
 ::players
 (fn [db _]
   (get-in db [:game :players])))

(rf/reg-sub
 ::latest-roll
 (fn [db _]
   (get-in db [:game :latest-roll])))
