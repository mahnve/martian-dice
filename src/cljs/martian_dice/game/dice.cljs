(ns martian-dice.game.dice
  (:require  [cljs.spec.alpha :as s]
             [cljs.spec.gen.alpha :as gen]))


(s/def ::score (s/or :positive pos-int? :zero zero?) )

(s/def ::die-side #{:cow :human :chicken :blast :spaceship})

(s/def ::dice (s/map-of ::die-side ::score))

(s/fdef roll-die
        :args (s/cat)
        :ret ::die-side)

(defn roll-die []
  (let [die (rand-int 6)]
    (case die
      0 :blast
      1 :chicken
      2 :cow
      3 :human
      :spaceship)))


(s/fdef roll-dice
        :args (s/cat :number pos-int?)
        :ret ::dice)

(defn roll-dice [number]
  (frequencies (repeatedly number roll-die)))


(def empty-dice-set
  {:cow 0
   :human 0
   :chicken 0
   :blast 0
   :spaceship 0})
