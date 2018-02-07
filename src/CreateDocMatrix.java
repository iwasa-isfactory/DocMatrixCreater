import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author iwasa
 *
 */

public class CreateDocMatrix {
	// properties
	private ArrayList<String[]> dfgf_lines;
	private ArrayList<String[]> sent_lines;
	private ArrayList<String[]> comp_lines;
	
	/**
	 * Constructor for CreateDocMatrix class
	 * @param dfgf_lines
	 * @param sent_lines
	 * @param comp_lines
	 */
	public CreateDocMatrix(ArrayList<String[]> dfgf_lines, ArrayList<String[]> sent_lines, ArrayList<String[]> comp_lines) {
		this.dfgf_lines = dfgf_lines;
		this.sent_lines = sent_lines;
		this.comp_lines = comp_lines;
	}
	
	/**
	 * Create DocMatrix by double array
	 * if option = 0 then return frequency matrix of term.
	 * if option = 1 then return TF matrix.
	 * if option = 2 then return TF-IDTF matrix.
	 * if option = 3 then return Normalized TF-IDF matrix. 
	 * @param option
	 * @return mat double[][]
	 */
	public double[][] create(int option) {
		int num_doc = sent_lines.size();
		int num_term = dfgf_lines.size();
		
		// create TermFreq Matrix
		double[][] mat = createMatrix(num_doc, num_term);
		
		// create TF Matrix
		if(option >= 1) {
			mat = createMatrixTF(mat, num_doc, num_term);
		}
		
		// create TFIDF Matrix
		if(option >= 2) {
			mat = createMatrixTFIDF(mat, num_doc, num_term);
		}
		
		// normalization
		if(option >= 3) {
			mat = normalization(mat, num_doc, num_term);
		}
		
		return mat;
	}
	
	/**
	 * Calculate distance matrix between documents.
	 * if opt = 'cos' then use 'cos similarity' for distance.
	 * if opt = 'L2' or another then use 'Euclidean distance' for distance.
	 * @param mat
	 * @param opt
	 * @return mat double[][]
	 */
	public double[][] distance(double[][] mat, String opt){
		int num_doc = sent_lines.size();

		// calculate distance between documents 
		double[][] dist = new double[num_doc][num_doc];
		if(opt == "cos") {
			for(int i=0; i < num_doc; i++) {
				for(int j=i+1; j < num_doc; j++) {
					dist[i][j] = calcCosDist(mat, i, j);
					dist[j][i] = calcCosDist(mat, j, i);
				}
			}
		}else {
			for(int i=0; i < num_doc; i++) {
				for(int j=i+1; j < num_doc; j++) {
					dist[i][j] = calcDist(mat, i, j);
					dist[j][i] = calcDist(mat, j, i);
				}
			}
		}
		return dist;
	}
	
	/**
	 * Make output lines
	 * @param mat
	 * @return o_lines String
	 */
	public String outputLines(double[][] mat) {
		int num_doc = sent_lines.size();
		int num_term = dfgf_lines.size();
		
		// create Output
		StringBuilder sb = new StringBuilder();
		sb.append("’PŒê/•¶‘ID,");
		for(Iterator<String[]> t_it = sent_lines.iterator(); t_it.hasNext();) {
			String term = t_it.next()[0];
			sb.append(term+",");
		}
		sb.setLength(sb.length() - 1);
		sb.append("\n");
		for(int i=0; i < num_term; i++) {
			sb.append( dfgf_lines.get(i)[0]+",");
			for(int j=0; j < num_doc; j++) {
				sb.append(mat[j][i]+",");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
        }
		String o_lines = sb.toString();
		return o_lines;
	}
	
	/**
	 * Make output lines for distance matrix
	 * @param mat
	 * @return o_lines String
	 */
	public String outputLinesForDist(double[][] mat) {
		int num_doc = sent_lines.size();
		
		// create Output
		StringBuilder sb = new StringBuilder();
		sb.append("•¶‘ID,");
		for(Iterator<String[]> t_it = sent_lines.iterator(); t_it.hasNext();) {
			String term = t_it.next()[0];
			sb.append(term+",");
		}
		sb.setLength(sb.length() - 1);
		sb.append("\n");
		for(int i=0; i < num_doc; i++) {
			sb.append( sent_lines.get(i)[0]+",");
			for(int j=0; j < num_doc; j++) {
				sb.append(mat[j][i]+",");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
        }
		String o_lines = sb.toString();
		return o_lines;
	}
	
	/**
	 * Create frequency matrix of term
	 * @param num_doc
	 * @param num_term
	 * @return mat double[][]
	 */
	private double[][] createMatrix(int num_doc, int num_term){
		double[][] mat = new double[num_doc][num_term];
		// calculate Frequency
		for(int i=0; i < num_doc; i++) {
			String sen = sent_lines.get(i)[1];
			for(int j=0; j < num_term; j++) {
				String term = dfgf_lines.get(j)[0].replace("\"", "");
				mat[i][j] = (sen.length() - sen.replaceAll(term, "").length()) / term.length();
			}
        }
		return mat;
	}
	
	/**
	 * Create TF matrix
	 * @param mat
	 * @param num_doc
	 * @param num_term
	 * @return mat double[][]
	 */
	private double[][] createMatrixTF(double[][] mat, int num_doc, int num_term){
		double[] tf_vec = new double[num_doc];
		// calculate TF
		for(int i=0; i < num_doc; i++) {
			for(int j=0; j < num_term; j++) {
				tf_vec[i] += mat[i][j];
			}
			for(int j=0; j < num_term; j++) {
				mat[i][j] /= tf_vec[i]; 
			}
		}
		return mat;
	}
	
	/**
	 * Create TF-IDTF matrix
	 * @param mat
	 * @param num_doc
	 * @param num_term
	 * @return mat double[][]
	 */
	private double[][] createMatrixTFIDF(double[][] mat, int num_doc, int num_term){
		int[] df_vec = new int[num_term];
		
		// calculate DF
		for(int j=0; j < num_term; j++) {
			String term = dfgf_lines.get(j)[0].replace("\"", "");
			int count = 0, num = 0, pre = 0;
			for(int i=0; i < comp_lines.size(); i++) {
				String comp = comp_lines.get(i)[1].replaceAll("\"", "");
				num = Integer.parseInt(comp_lines.get(i)[0].replaceAll("\"", ""));
				count += comp.equals(term)? 1: 0;
				if(pre != num) {
					pre = num;
				}
			}
			df_vec[j] = (count > 0)? 1 : 0;
		}
		
		// create doc Matrix
		double log_2 = Math.log(2);
		for(int i=0; i < num_term; i++) {
			for(int j=0; j < num_doc; j++) {
				double tmp = num_doc / df_vec[j] + 1;
				mat[j][i] = mat[j][i] * Math.log(tmp) / log_2;
			}
        }
		return mat;
	}
	
	/**
	 * Create Normalized TF-IDF matrix
	 * @param mat
	 * @param num_doc
	 * @param num_term
	 * @return mat double[][]
	 */
	private double[][] normalization(double[][] mat, int num_doc, int num_term){
		double[] lens = new double[num_doc];
		
		// calculate Size of Vector 
		for(int j=0;j < num_doc; j++) {
			float sum = 0;
			for(int i=0; i < num_term; i++) {
				sum += mat[j][i] * mat[j][i];
			}
			lens[j] = Math.sqrt(sum);
		}

		// create doc Matrix
		for(int i=0; i < num_term; i++) {
			for(int j=0; j < num_doc; j++) {
				if(lens[j] == 0) continue; 
				mat[j][i] /= lens[j];
			}
        }
		return mat;
	}
	
	/**
	 * Calculate distance between documents
	 * @param mat
	 * @param u
	 * @param v
	 * @return val double
	 */
	private double calcDist(double[][] mat, int u, int v) {
		double val = 0;
		int num_term = dfgf_lines.size();
		for(int i=0; i < num_term; i++) {
			val += (mat[u][i] - mat[v][i]) * (mat[u][i] - mat[v][i]);
		}
		val = Math.sqrt(val);
		return val;
	}
	
	/**
	 * Calculate distance by 'cos similarity' between documents
	 * @param mat
	 * @param u
	 * @param v
	 * @return val double
	 */
	private double calcCosDist(double[][] mat, int u, int v) {
		double val = 0;
		double u_sum = 0, v_sum = 0, uv_sum = 0;
		int num_term = dfgf_lines.size();
		for(int i=0; i < num_term; i++) {
			u_sum += mat[u][i] * mat[u][i];
			v_sum += mat[v][i] * mat[v][i];
			uv_sum += mat[u][i] * mat[v][i];
		}
		if(u_sum == 0 || v_sum == 0) {
			val = 0;
		}else {
			val = uv_sum / Math.sqrt(u_sum) * Math.sqrt(v_sum);
		}
		return val;
	}
}
