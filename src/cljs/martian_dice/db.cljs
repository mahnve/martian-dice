(ns martian-dice.db)

(def default-db
  {:name "martian-dice"
   :unused-dice 14
   :selected-dice {:cow 0
                   :human 0
                   :chicken 0
                   :blast 0
                   :spaceship 0}

   :players [{:name "hej"
              :points 3}]})
