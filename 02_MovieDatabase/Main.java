import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.IllegalThreadStateException;
import java.lang.Process;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

	public static final long sec = 1000000000L, msec = 1000000L;
	public static final long limit = 200 * sec;

	public static final List<String> bannedClasses = Arrays.asList(new String[] {
		"java/util/ArrayList", "java/util/Vector", "java/util/LinkedList"
	});

	public static void main(String[] args) {
		MovieDatabaseConsole is_mdc_class = null;
		int i;
		try {
			System.out.print("Analyzing your class file...");
			clarity("MovieDatabaseConsole.class");
			System.out.println(" Good to go!");
		} catch (Exception err) {
			System.out.println("\nAn error occurred while analyzing your class file.");
			System.out.println("Use this program cautiously at your own risk.");
			System.out.println("Error report:\n");
			err.printStackTrace();
			System.out.println();
		}
		File in, out;
		long mx = -1;
		for (i=1; (in = new File("data/i" + i + ".in")).exists() && (out = new File("data/o" + i + ".out")).exists(); ++i) {
			try {
				System.out.print("\rtest case #" + i + "...");
				ProcessBuilder pb = new ProcessBuilder("java", "-Xms128M", "-Xmx128M", "MovieDatabaseConsole");
				pb.directory(new File("."));
				pb.redirectInput(in);
				pb.redirectOutput(new File("y.out"));
				pb.redirectError(new File("y.err"));
				Process p = pb.start();
				long st = System.nanoTime(), ed;
				while ((ed = System.nanoTime()) - st < limit) {
					try {
						if (p.exitValue() != 0) {
							System.out.println("\rRuntime error on test " + i + ". Cannot continue testing.");
							return;
						} else {
							break;
						}
					} catch (IllegalThreadStateException err) {
					}
				}
				mx = ed - st > mx ? ed - st : mx;
				if (ed - st >= limit) {
					System.out.println("\rTime limit exceeded on test " + i + ". Cannot continue testing.");
					p.destroy();
					return;
				}
				BufferedReader br = new BufferedReader(new FileReader(new File("y.out")));
				BufferedReader br2 = new BufferedReader(new FileReader(out));
				String s, s2;
				while ((s2 = br2.readLine()) != null) {
					s = br.readLine();
					if (!s2.equals(s)) {
						if (s2.equals(s.trim())) {
							System.out.println("\rWrong output format on test " + i + ". Cannot continue testing.");
						} else {
							System.out.println("\rWrong answer on test " + i + ". Cannot continue testing.");
						}
						return;
					}
				}
				if ((s = br.readLine()) != null) {
					System.out.println("\rWrong answer on test " + i + ". Cannot continue testing.");
					return;
				}
				br.close();
				br2.close();
				br = new BufferedReader(new FileReader(new File("y.err")));
				if ((s = br.readLine()) != null) {
					System.out.println("\rError stream used on test " + i + ". Cannot continue testing.");
					return;
				}
			} catch (IOException err) {
				err.printStackTrace();
				return;
			}
		}
		if (--i == 0) {
			System.out.println("No test cases found!");
			System.out.println("Perhaps missing \"data\" folder?");
		} else {
			System.out.println("\rYou passed all " + i + " test case" + (i == 1 ? "" : "s") + "! (" + (mx / msec) + "ms)");
		}
	}

	public static void clarity(String name) throws Exception {
		Analyzer an = new Analyzer(name);
		int i;
		for (i=0; i<an.pool.length; ++i) {
			Analyzer.Pool ap = an.pool[i];
			if (ap instanceof Analyzer.MethodPool) {
				ap = an.pool[((Analyzer.MethodPool)ap).v];
			}
			if (ap instanceof Analyzer.ClassPool) {
				String cname = ((Analyzer.UTFPool)an.pool[((Analyzer.ClassPool)ap).v]).toString(an);
				if (bannedClasses.contains(cname)) {
					throw new Exception("The class " + name + " uses banned class " + cname.replace("/", "."));
				}
			}
		}
	}

}
