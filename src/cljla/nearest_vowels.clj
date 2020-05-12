(ns cljla.nearest-vowels
  "[Distance to nearest vowel](https://purelyfunctional.tv/issues/purelyfunctional-tv-newsletter-376-learn-from-core/)

  Write a function that takes a string as argument. Each character in the string
  will be a letter. The function should return a sequence containing the
  distances from each corresponding letter in the string to the nearest vowel in
  the string.

  For example:

  (nearest-vowels \"aeiou\") ;=> [0 0 0 0 0]  ;; if the letter is a vowel, the distance is 0
  (nearest-vowels \"babbb\" ;=> [1 0 1 2 3]
  (nearest-vowels \"babbba\") ;=> [1 0 1 2 1 0]

  Notes:

      All input strings will contain at least one vowel and all letters.
      Vowels are a, e, i, o, and u.
  ")

(defn nearest-vowel-in-alphabet
  "Calculate nearest vowel in the the alphabet. 'Cause I misread the spec."
  [character]
  (let [vowels (seq "aeiou")
        abs    #(max % (- %))]
    (apply min (map #(abs (- (int %) (int character))) vowels))))

(defn nearest-vowels*
  "Calculates the nearest vowel for each letter in a string. This solution
  builds an index tuple for each char in the string in the shape of
  `'([idx char vowel?] ...)` which is then used to calculate the final output.
  Tries to favor readability over conciseness.

  Performance using map:
   10K - Elapsed time:    55.198788 msecs
  100K - Elapsed time: 17784.047142 msecs
    1M - :(

  Performance using pmap:
    1K - Elapsed time: 17.755938 msecs
   10K - Elapsed time: 124.613377 msecs
  100K - Elapsed time: 3884.350658 msecs
    1M - Elapsed time: 23206.325645 msecs
  "
  [string]
  (let [abs #(max % (- %))
        vowels (set "aeiou")
        vowel? #(vowels %)
        index (map #(vector %1 %2 (vowel? %3)) (iterate inc 0) string string)
        vowel-positions (sequence (comp (filter last) (map first)) index)
        distance (fn [[i]] (apply min (map #(abs (- i %)) vowel-positions)))]
    (pmap distance index)))


(defn distance-to-nearest
  "Finds the distance to the nearest index value in collection."
  [indexes value]
  (let [low  (first indexes)
        high (last indexes)]
    (cond
      (empty? indexes) '()
      (<= value low)  (- low value)
      (>= value high) (- value high)
      :else
      (some (fn [[fst lst]]
              (when (<= fst value lst)
                (min (- value fst) (- lst value))))
            (map vector indexes (rest indexes))))))

(defn nearest-vowels
  "Calculates the nearest vowel for each letter in a string.
  This solution adds preformance improvements.

  Performance using pmap:
    1K - Elapsed time:    22.523197 msecs
   10K - Elapsed time:    85.872171 msecs
  100K - Elapsed time: 11303.78823 msecs
    1M - Elapsed time: 
  "
  [string]
  (let [vowels (set "aeiou")
        vowel-index (keep-indexed #(when (vowels %2) %1) string)]
    (map (partial distance-to-nearest vowel-index)
         (range 0 (count string)))))

(comment
  (require '[clojure.test.check.generators :as gen]
           '[cljla.nearest-vowels :as nv])
  (let [n 1000
        s (gen/generate (gen/vector gen/char-alpha) n)]
    (time (doall (nv/nearest-vowels s)))
    nil))
