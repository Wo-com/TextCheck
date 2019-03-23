package algorithm;

//https://codereview.stackexchange.com/questions/182601/optimizing-needleman-wunsch-algorithm-in-java

public class DNASequence {
	protected final String seq_1, seq_2;    //要分析的两个序列
	public int alignmentScore;              //使用Needleman-Wunsch算法
	protected Node[][] matrix;              //存储分数和缩进
	protected final int matchScore, mismatchScore, indel;

	//用于打印DNA序列分析的字符串
	String top = "";        // Sequence 1
	String bottom = "";     // Sequence 2

	public DNASequence(String s1, String s2) {
		//我使用一个■作为缓冲,这样序列字符串正确对齐
		// 在矩阵中使用缩进和分数。
		ScoreScheme s = new ScoreScheme(2, -1, -2);  
		seq_1 = "\u25A0" + s1;
		seq_2 = "\u25A0" + s2;
		matchScore = s.matchScore;
		mismatchScore = s.mismatchScore;
		indel = s.indel;

		matrix = new Node[seq_1.length()][seq_2.length()];
		for (int i = 0; i < seq_1.length(); i++)
			matrix[i][0] = new Node(i * indel, i, 0);

		for (int i = 0; i < seq_2.length(); i++)
			matrix[0][i] = new Node(i * indel, 0, i);
	}

	//辅助方法，帮助决定使用哪种匹配/不匹配得分。
	protected int score(int i, int j) {
		if (seq_1.charAt(i) == seq_2.charAt(j))
			return matchScore;
		else
			return mismatchScore;
	}

	//在本地级别上实现Needleman-Wunsch algo的Helper方法。
	protected Node match(int i, int j) {
		Node s1,s2,s3;
		s1 = new Node(matrix[i-1][j-1].score + score(i, j), i, j);
		s2 = new Node(matrix[i-1][j].score + indel, i, j);
		s3 = new Node(matrix[i][j-1].score + indel, i, j);

		Node largest = new Node(Math.max(s1.score, Math.max(s2.score, s3.score)), i, j);
		if (s1.compareTo(largest) == 0)
			largest.prev = matrix[i-1][j-1];
		else if(s2.compareTo(largest) == 0)
			largest.prev = matrix[i-1][j];
		else
			largest.prev = matrix[i][j-1];
		return largest;
	}

	public Node runAnalysis() {
		for (int i = 1; i < seq_1.length(); i++) {
			for (int j = 1; j < seq_2.length(); j++){
				matrix[i][j] = match(i, j);
			}
		}
		alignmentScore = matrix[seq_1.length()-1][seq_2.length()-1].score;
		return matrix[seq_1.length()-1][seq_2.length()-1];
	}

	//辅助方法，逐步构建分析结果。它将返回
	//“尾巴”，因为我们可能还需要做一些工作。
	protected Node traceHelper(Node curr) {
		while (curr.prev != null) {
			if (curr.i - curr.prev.i == 1 && curr.j - curr.prev.j == 1){    // If the path leads diagonal
				boolean x = seq_1.charAt(curr.i) == seq_2.charAt(curr.j) ? true : false;
				if(x){
					top = seq_1.charAt(curr.i) +top;
					bottom = seq_2.charAt(curr.j) +bottom;
				}else{
					top = seq_1.charAt(curr.i) + top;
					bottom = seq_2.charAt(curr.j) + bottom;
				}
			}else if (curr.i - curr.prev.i == 1){               //如果这条路通向山顶
				top = seq_1.charAt(curr.i) + top;
				bottom = "-" +  bottom;                     //如果这条路通向左边
			}else if (curr.j - curr.prev.j == 1){
				top = "-" + top;
				bottom = seq_2.charAt(curr.j) +  bottom;
			}
			curr = curr.prev;
		}
		return curr;
	}

//从矩阵的最后一个节点回溯到第一个索引节点。
	public void traceback() {
		Node curr = matrix[seq_1.length()-1][seq_2.length()-1];
		curr = traceHelper(curr);
		while (curr.i != 0 || curr.j != 0) {
			if (curr.i != 0 && curr.j == 0){
				curr.prev = matrix[curr.i-1][curr.j];
				curr = traceHelper(curr);
			}else if (curr.i == 0 && curr.j != 0) {
				curr.prev = matrix[curr.i][curr.j-1];
				curr = traceHelper(curr);
			}
		}

	//打印DNA序列分析
//		System.out.println(top);
//		System.out.println(bottom);
	}
	public String getString1(){
		return top;
	}
	public String getString2(){
		return bottom;
	}	

}
class Node implements Comparable<Node>{
	int i, j;
	int score;
	Node prev;

	public Node(int score, int x, int y) {
		this.i = x;
		this.j = y;
		this.score = score;
		this.prev = null;
	}
	public int compareTo(Node n) {
		return this.score - n.score;
	}
	public String toString() {
		return ""+score;
	}
}

class ScoreScheme {
	int matchScore, mismatchScore, indel;
	public ScoreScheme(int m1, int m2, int i) {
		matchScore = m1;
		mismatchScore = m2;
		indel = i;
	}
}