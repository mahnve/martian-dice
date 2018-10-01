(defproject martian-dice "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [reagent "0.7.0"]
                 [re-frame "0.10.4"]
                 [secretary "1.2.3"]
                 [lein-doo "0.1.8"]
                 [ns-tracker "0.3.1"]
                 [stylefy "1.2.0"]
                 [com.taoensso/timbre "4.10.0"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-doo "0.1.8"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "resources/public/css"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles
  {:doo
   {:build "test"
    }
   :dev
   {:dependencies [[binaryage/devtools "0.9.9"]
                   [org.clojure/test.check "0.9.0"]
                   [cljsjs/d3 "4.12.0-0"]
                   [day8.re-frame/trace "0.1.18"]
                   [figwheel-sidecar "0.5.14"]
                   [com.cemerick/piggieback "0.2.2"]]

    :plugins      [[lein-figwheel "0.5.14"]
                   [lein-kibit "0.1.5"]
                   [lein-ancient "0.6.14"]]}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs" "test/cljs"]
     :figwheel     {:on-jsload "martian-dice.core/mount-root"}
     :compiler     {:main                 martian-dice.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/dev-out"
                    :asset-path           "js/compiled/dev-out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload
                                           day8.re-frame.trace.preload]
                    :closure-defines      {"re_frame.trace.trace_enabled_QMARK_" true}
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}
    {:id "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler {:output-to "resources/public/js/testable.js"
                :output-dir "resources/public/js/compiled/test-out"
                :main martian-dice.runner
                :optimizations :none}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            martian-dice.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :output-dir       "resources/public/js/compiled/min-out"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}]})
