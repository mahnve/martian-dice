(ns martian-dice.game)

(defn roll-die []
  (let [die (rand-int 6)]
    (case die
      0 :blast
      1 :chicken
      2 :cow
      3 :human
      :spaceship)))

(defn roll-dice [number]
  (frequencies (repeatedly number roll-die)))
