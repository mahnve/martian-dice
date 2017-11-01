(ns martian-dice.game)

(defn roll-die []
  (let [die (rand-int 6)]
   (cond
        (= die 0) :blast
        (= die 1) :chicken
        (= die 2) :cow
        (= die 3) :human
        :else :spaceship)))

(defn roll-dice [number]
  (frequencies (repeatedly number roll-die)))


