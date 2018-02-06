■DocMatrixCreater.jarの使い方
　■コマンドライン
　　usages: java -jar DocMatrixCreater.jar [encoding]
　　example: java -jar DocMatrixCreater.jar UTF-8

　■入出力
　　入出力はファイルで指定する。
　　入力：
　　　DocMatrixCreater.jarと同階層にinputフォルダを作成し、複合語エンジンの出力結果を配置する。
　　　必要なファイルは、sentence.csv、sentence_dfgf.csv。
　　出力：
　　　DocMatrixCreater.jarと同階層のoutputフォルダに出力ファイルが作成される。
　　　※outputフォルダが存在しない場合はエラーとなる。
　　　出力ファイルは、doc_matrix_tf.csv、doc_matrix_tfidf.csv。
　　　文書行列を単語出現頻度によって作成したdoc_matrix_tf.csv。
　　　文書行列を単語出現頻度に文書内単語出現頻度の逆数をかけた値で作成したdoc_matrix_tfidf.csv。

　■Remark
　　本ツールはbodaisアプリケーションの複合語エンジンを実行した結果ファイルに対して、実行することを前提にしている。
　　しかし、現行は複合語エンジンのUIからは入力ファイルとなるsentence.csv、sentence_dfgf.csvは取得できない。

