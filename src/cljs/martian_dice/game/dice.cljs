(ns martian-dice.game.dice
  (:require  [cljs.spec.alpha :as s]))


(s/def ::cow (s/and int? #(>= % 0)))
(s/def ::human (s/and int? #(>= % 0)))
(s/def ::chicken (s/and int? #(>= % 0)))
(s/def ::blast (s/and int? #(>= % 0)))
(s/def ::spaceship (s/and int? #(>= % 0)))


(s/def ::dice (s/keys :req [::cow ::human ::chicken ::blast ::spaceship]))

(s/fdef roll-die
        :ret ::dice)

(defn roll-die []
  (let [die (rand-int 6)]
    (case die
      0 ::blast
      1 ::chicken
      2 ::cow
      3 ::human
      ::spaceship)))

(defn roll-dice [number]
  (frequencies (repeatedly number roll-die)))

(def empty-dice-set
  {::cow 0
   ::human 0
   ::chicken 0
   ::blast 0
   ::spaceship 0})
