import java.util.ArrayList;
import java.util.Iterator;

public class CreateDocMatrix {
	// properties
	private ArrayList<String[]> dfgf_lines;
	private ArrayList<String[]> sent_lines;
	
	public CreateDocMatrix(ArrayList<String[]> dfgf_lines, ArrayList<String[]> sent_lines) {
		this.dfgf_lines = dfgf_lines;
		this.sent_lines = sent_lines;
	}
	
	public String create(String option) {
		int num_doc = sent_lines.size();
		int num_term = dfgf_lines.size();
		double[][] mat = new double[num_doc][num_term];
		int[] df_vec = new int[num_term];
		
		// create TF Matrix
		for(int i=0; i < num_doc; i++) {
			String sen = sent_lines.get(i)[1];
			for(int j=0; j < num_term; j++) {
				String term = dfgf_lines.get(j)[0].replace("\"", "");
				mat[i][j] = (sen.length() - sen.replaceAll(term, "").length()) / term.length();
			}
        }
		
		// create DF Vector
		if(option.equals("TFIDF")) {
			for(int j=0; j < num_term; j++) {
				String term = dfgf_lines.get(j)[0].replace("\"", "");
				int count = 0;
				for(int i=0; i < num_doc; i++) {
					String sen = sent_lines.get(i)[1];
					count += sen.contains(term)? 1: 0;
				}
				df_vec[j] = count;
			}
		}
		
		// create Output
		StringBuilder sb = new StringBuilder();
		sb.append("’PŒê/•¶‘ID,");
		for(Iterator<String[]> t_it = sent_lines.iterator(); t_it.hasNext();) {
			String term = t_it.next()[0];
			sb.append(term+",");
		}
		sb.setLength(sb.length() - 1);
		sb.append("\n");
		double log_2 = Math.log(2);
		for(int i=0; i < num_term; i++) {
			sb.append( dfgf_lines.get(i)[0]+",");
			for(int j=0; j < num_doc; j++) {
				if(option.equals("TFIDF")) {
					double tmp = (df_vec[j]!=0)? num_doc / df_vec[j]: 1;
					mat[j][i] = mat[j][i] * Math.log(tmp) / log_2;
				}
				sb.append(mat[j][i]+",");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
        }
		String o_lines = sb.toString();
		return o_lines;
	}
}
