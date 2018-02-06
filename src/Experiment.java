import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Experiment {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String delimiter = ",";
		if(args.length == 0) {
			System.out.println("入力ファイルの文字コードを指定してください。");
			System.out.println("指定可能な文字コードは『UTF-8』または『Shift-JIS』");
			return;
		}
		String encode = args[0];
		String sent_path = "input/sentence.csv";
		String dfgf_path = "input/sentence_dfgf.csv";
		ArrayList<String[]> dfgf_lines = null;
		ArrayList<String[]> sent_lines = null;
		FileOperator fo = new FileOperator();
		try {
			dfgf_lines = fo.importer(dfgf_path, encode, delimiter, true);
			sent_lines = fo.importer(sent_path, encode, delimiter, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateDocMatrix dm =  new CreateDocMatrix(dfgf_lines, sent_lines);
		String o_lines = dm.create("TF");
		fo.exporter("output/doc_matrix_tf.csv", o_lines);
		o_lines = dm.create("TFIDF");
		fo.exporter("output/doc_matrix_tfidf.csv", o_lines);
	}
	
}

