(ns martian-dice.game.game
  (:require [martian-dice.game.dice :as dice]
            [cljs.spec.alpha :as s]
            [cljs.spec.gen.alpha :as gen]))


(s/def ::no-of-dice pos-int?)
(s/def ::selected-dice (s/or :die-side ::dice/die-side :none nil?))
(s/def ::saved-dice ::dice/dice)
(s/def ::latest-roll ::dice/dice)

(s/def ::game (s/keys :req [::no-of-dice ::selected-dice ::saved-dice ::latest-roll]))

(s/fdef roll-dice
        :args (s/cat :game ::game)
        :ret ::game)

(gen/sample  (s/gen ::game))

(defn roll-dice [game]
  (assoc game :latest-roll (dice/roll-dice (:no-of-dice game))))

(s/fdef select-dice
        :args (s/cat :game ::game :dice ::dice/die-side)
        :ret ::game)

(defn select-dice [game dice]
  (assoc game :selected-dice dice))

(defn selected-dice [game]
  [(get game :selected-dice) ::dice/blast] )

(defn no-of-dice [game selected-dice]
  (get-in game [:latest-roll selected-dice]))

(defn can-select-dice [game dice]
  (> (no-of-dice game dice) 0)

  (defn save-selected-dice
    [{:keys [:selected-dice] :as game}]
    (if (can-select-dice game selected-dice)
      (assoc-in game
                [:saved-dice selected-dice]
                (no-of-dice game selected-dice)))))

(defn clear-latest-roll [game]
  (assoc game :latest-roll dice/empty-dice-set))


(defn saved-dice [game]
  (:saved-dice game))

(defn end-turn [game]
  (-> game
      (save-selected-dice)
      (clear-latest-roll)))

(defn new-game 
  ([]
   {::current-player "Foo Bar"
    ::new-turn true
    ::no-of-dice 14
    ::selected-dice nil
    ::saved-dice dice/empty-dice-set
    ::latest-roll dice/empty-dice-set
    ::players [{:name "Foo Bar"
                :score 0}]})
  ([_]
   (new-game)))

