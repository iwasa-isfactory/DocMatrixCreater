■DocMatrixCreater.jarの使い方
　■コマンドライン
　　usages: java -jar DocMatrixCreater.jar [encoding] [option]
　　example: java -jar DocMatrixCreater.jar UTF-8 1
　　※変数optionを指定した場合はTFIDFの値が規格化されます。

　■入出力
　　入出力はファイルで指定する。
　　入力：
　　　DocMatrixCreater.jarと同階層にinputフォルダを作成し、複合語エンジンの出力結果を配置する。
　　　必要なファイルは、sentence.csv、sentence_dfgf.csv、sentence_wc.csv。
　　出力：
　　　DocMatrixCreater.jarと同階層のoutputフォルダに出力ファイルが作成される。
　　　※outputフォルダが存在しない場合はエラーとなる。
　　　出力ファイルは、doc_matrix_tFrec.csv、doc_matrix_tf.csv、doc_matrix_tfidf.csv、doc_matrix_N_tfidf.csv
　　　doc_matrix_L2dist.csv、doc_matrix_cosdist.csv。
　　　文書行列を単語出現頻度によって作成したdoc_matrix_tFreq.csv
　　　文書行列を各文書の単語出現頻度を全体の文書集合の単語出現頻度で割った値で作成したdoc_matrix_tf.csv。
　　　文書行列を単語出現頻度に文書内単語出現頻度の逆数をかけた値で作成したdoc_matrix_tfidf.csv。
　　　doc_matrix_tfidf.csvを規格化した文書行列doc_matrix_N_tfidf.csv
　　　文書間距離をL2ノルムで計量したときの文書間距離行列doc_matrix_L2dist.csv。
　　　文書間距離をCOS類似度で計量したときの文書間距離行列doc_matrix_cosdist.csv。

　■Remark
　　本ツールはbodaisアプリケーションの複合語エンジンを実行した結果ファイルに対して、実行することを前提にしている。
　　しかし、現行は複合語エンジンのUIからは入力ファイルとなるsentence.csv、sentence_dfgf.csvは取得できない。
　　TF-IDF:
　　https://takuti.me/note/tf-idf/
