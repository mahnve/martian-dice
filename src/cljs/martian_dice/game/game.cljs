(ns martian-dice.game.game
  (:require [martian-dice.game.dice :as dice]
            [cljs.spec.alpha :as s]
            [cljs.spec.gen.alpha :as gen]
            [taoensso.timbre :as log]))

(s/def ::no-of-dice pos-int?)
(s/def ::selected-dice (s/or :die-side ::dice/die-side :none nil?))
(s/def ::saved-dice ::dice/dice)
(s/def ::latest-roll ::dice/dice)

(s/def ::game (s/keys :req [::no-of-dice ::selected-dice ::saved-dice ::latest-roll]))

(s/fdef roll-dice
        :args (s/cat :game ::game)
        :ret ::game)

(s/fdef select-dice
        :args (s/cat :game ::game :dice ::dice/die-side)
        :ret ::game)

(defn select-dice [game dice]
  (assoc game ::selected-dice dice))

(s/fdef selected-dice
        :args (s/cat)
        :ret ::dice/die-side)

(defn selected-dice [game]
  [(get game ::selected-dice) ::dice/blast] )

(defn total-no-of-dice [game]
  (::no-of-dice game))

(defn no-of-rolled-dice [game selected-dice]
  (get-in game [::latest-roll selected-dice]))

(defn no-of-saved-dice [game]
  (reduce + (vals (::saved-dice game))))

(defn can-select-dice [game dice]
  (> (no-of-rolled-dice game dice) 0))

(defn save-selected-dice
  [{:keys [::selected-dice] :as game}]
  (if (can-select-dice game selected-dice)
    (assoc-in game
              [::saved-dice selected-dice]
              (no-of-rolled-dice game selected-dice))))

(defn no-of-blasts [game]
  (get-in game [::saved-dice ::dice/blast]))

(defn no-of-dice-to-roll [game]
  (log/info "rolling dice")
  (log/info game)
  (log/info (str "total no of dice:" (total-no-of-dice game)))
  (log/info (str "no of selected dice: " (no-of-saved-dice game)))
  (- (total-no-of-dice game)
     (no-of-saved-dice game)))

(defn roll-dice [game]
  (let [latest-roll (dice/roll-dice (no-of-dice-to-roll game))]
    (assoc game ::latest-roll latest-roll)))

(defn clear-latest-roll [game]
  (assoc game ::latest-roll dice/empty-dice-set))

(defn saved-dice [game]
  (::saved-dice game))

(defn latest-roll [game]
  (::latest-roll game))


(defn save-blasts [game]
  (assoc-in game [::saved-dice ::dice/blasts] (no-of-blasts game)))

(defn clear-selected-dice [game]
  (assoc game ::selected-dice nil))

(defn prepare-throw [game]
  (-> game
      save-blasts
      save-selected-dice
      clear-selected-dice
      clear-latest-roll))

(defn end-turn [game]
  (-> game
      save-blasts
      save-selected-dice
      clear-selected-dice
      clear-latest-roll))

(defn new-game
  ([] {::current-player "Foo Bar"
       ::new-turn true
       ::no-of-dice 14
       ::selected-dice nil
       ::saved-dice dice/empty-dice-set
       ::latest-roll dice/empty-dice-set
       ::players [{:name "Foo Bar"
                   :score 0}]})
  ([_] (new-game)))
