(ns martian-dice.subs
  (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (get-in db [:view-state :active-panel] )))

(re-frame/reg-sub
 ::players
 (fn [db _]
   (get-in db [:game :players])))

(re-frame/reg-sub
 ::latest-roll
 (fn [db _]
   (get-in db [:game :latest-roll])))
