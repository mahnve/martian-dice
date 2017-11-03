(ns martian-dice.game.game
  (:require [martian-dice.game.dice :as dice]))

(defn roll-dice[game]
  (assoc game :latest-roll (dice/roll-dice (:no-of-dice game))))

(defn select-dice [game dice]
  (assoc game :selected-dice dice))

(defn selected-dice [game]
  [(get game :selected-dice) ::dice/blast] )

(def new-game
  {:current-player "Foo Bar"
   :no-of-dice 14
   :selected-dice nil
   :saved-dice dice/empty-dice-set
   :latest-roll dice/empty-dice-set
   :players [{:name "Foo Bar"
              :score 0}]})

(defn end-round [game])
