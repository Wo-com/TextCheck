package algorithm;

public class Test {

	public static void main(String[] args) {
		 String seq_1 = "CATTAATTACACTCTCGCACTCACCAAACCCAGACAGGCCTCGACTCC";
		 String seq_2 = "ACTAAACAAGACTCGCCTGTCTAACTAGGGAGTTTATAATGAACCGTGGCGTAGACCA";

		DNASequence dna=new DNASequence(seq_1,seq_2);
		dna.runAnalysis();
		dna.traceback();
		System.out.print(dna.getString1());
	}

}
