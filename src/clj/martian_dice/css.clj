(ns martian-dice.css
  (:require [garden.def :refer [defstyles]]
            [garden.selectors :as gs]))

(gs/defclass selected)

(defstyles screen
  [:li {:list-style :none}
   [:&.selected {:background-color :#DDD}]])
