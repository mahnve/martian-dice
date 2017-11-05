(ns martian-dice.game.game
  (:require [martian-dice.game.dice :as dice]))

(defn roll-dice [game]
  (assoc game :latest-roll (dice/roll-dice (:no-of-dice game))))

(defn select-dice [game dice]
  (assoc game :selected-dice dice))

(defn selected-dice [game]
  [(get game :selected-dice) ::dice/blast] )

(defn no-of-dice [game type]
  (get-in game [:latest-roll selected-dice]))

(defn can-select-dice [game dice]
  (> 0 (no-of-dice game dice)))

(defn save-selected-dice
  [{:keys [:selected-dice] :as game}]
  (if (can-select-dice game selected-dice)
    (assoc-in game
              [:saved-dice selected-dice]
              (no-of-dice game selected-dice))))

(defn clear-latest-roll [game]
  (assoc game :latest-roll dice/empty-dice-set))


(defn saved-dice [game]
  (:saved-dice game))

(defn end-turn [game]
  (-> game
      (save-selected-dice)
      (clear-latest-roll)))

(defn new-game [_]
  {:current-player "Foo Bar"
   :new-turn true
   :no-of-dice 14
   :selected-dice nil
   :saved-dice dice/empty-dice-set
   :latest-roll dice/empty-dice-set
   :players [{:name "Foo Bar"
              :score 0}]})

