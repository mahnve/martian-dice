(ns martian-dice.db)

(def new-game
  {:current-player "Foo Bar"
   :no-of-dice 14
   :selected-dice nil
   :saved-dice {:cow 0
                :human 0
                :chicken 0
                :blast 0
                :spaceship 0}
   :latest-roll {:cow 0
                 :human 0
                 :chicken 0
                 :blast 0
                 :spaceship 0}
   :players [{:name "Foo Bar"
              :score 0}]})

(def default-db
  {:name "martian-dice"
   :view-state {:active-panel :home-panel
                :game-panel {:selected-dice nil}}
   :game new-game})
