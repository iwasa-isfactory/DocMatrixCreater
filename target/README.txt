��DocMatrixCreater.jar�̎g����
�@���R�}���h���C��
�@�@usages: java -jar DocMatrixCreater.jar [encoding] [option]
�@�@example: java -jar DocMatrixCreater.jar UTF-8 1
�@�@���ϐ�option���w�肵���ꍇ��TFIDF�̒l���K�i������܂��B

�@�����o��
�@�@���o�͂̓t�@�C���Ŏw�肷��B
�@�@���́F
�@�@�@DocMatrixCreater.jar�Ɠ��K�w��input�t�H���_���쐬���A������G���W���̏o�͌��ʂ�z�u����B
�@�@�@�K�v�ȃt�@�C���́Asentence.csv�Asentence_dfgf.csv�Asentence_wc.csv�B
�@�@�o�́F
�@�@�@DocMatrixCreater.jar�Ɠ��K�w��output�t�H���_�ɏo�̓t�@�C�����쐬�����B
�@�@�@��output�t�H���_�����݂��Ȃ��ꍇ�̓G���[�ƂȂ�B
�@�@�@�o�̓t�@�C���́Adoc_matrix_tFrec.csv�Adoc_matrix_tf.csv�Adoc_matrix_tfidf.csv�Adoc_matrix_N_tfidf.csv
�@�@�@doc_matrix_L2dist.csv�Adoc_matrix_cosdist.csv�B
�@�@�@�����s���P��o���p�x�ɂ���č쐬����doc_matrix_tFreq.csv
�@�@�@�����s����e�����̒P��o���p�x��S�̂̕����W���̒P��o���p�x�Ŋ������l�ō쐬����doc_matrix_tf.csv�B
�@�@�@�����s���P��o���p�x�ɕ������P��o���p�x�̋t�����������l�ō쐬����doc_matrix_tfidf.csv�B
�@�@�@doc_matrix_tfidf.csv���K�i�����������s��doc_matrix_N_tfidf.csv
�@�@�@�����ԋ�����L2�m�����Ōv�ʂ����Ƃ��̕����ԋ����s��doc_matrix_L2dist.csv�B
�@�@�@�����ԋ�����COS�ގ��x�Ōv�ʂ����Ƃ��̕����ԋ����s��doc_matrix_cosdist.csv�B

�@��Remark
�@�@�{�c�[����bodais�A�v���P�[�V�����̕�����G���W�������s�������ʃt�@�C���ɑ΂��āA���s���邱�Ƃ�O��ɂ��Ă���B
�@�@�������A���s�͕�����G���W����UI����͓��̓t�@�C���ƂȂ�sentence.csv�Asentence_dfgf.csv�͎擾�ł��Ȃ��B
�@�@TF-IDF:
�@�@https://takuti.me/note/tf-idf/
