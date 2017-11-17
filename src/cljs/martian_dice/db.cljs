(ns martian-dice.db
  (:require [martian-dice.game.game :as game]
            [taoensso.timbre :as log]))

(defn with-game
  ([db f]
   (update db :game f)))

(defn with-view [db view-panel]
  (assoc-in db [:view-state :active-panel] view-panel))

(def default-db
  {:name "martian-dice"
   :view-state {:active-panel :home-panel
                :game-panel {:selected-dice nil}}
   :game (game/new-game)})
