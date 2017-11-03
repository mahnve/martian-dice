(ns martian-dice.db
  (:require [martian-dice.game.game :as game]))


(def default-db
  {:name "martian-dice"
   :view-state {:active-panel :home-panel
                :game-panel {:selected-dice nil}}
   :game game/new-game})
