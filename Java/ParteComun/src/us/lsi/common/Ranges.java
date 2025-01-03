package us.lsi.common;

/**
 * @author migueltoro
 *
 * Rangos de valores de tipos num�ricos
 */
public class Ranges {

	public static class IntRange {
		public final Integer a;
		public final Integer b;
		public final Integer c;
		public static IntRange of(Integer a, Integer b, Integer c) {
			return new IntRange(a,b,c);
		}
		public static IntRange of(Integer a, Integer b) {
			return new IntRange(a,b,1);
		}
		public IntRange(Integer a, Integer b, Integer c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
		public Boolean isEmpty() {
			return a>=b;
		}
		public Boolean contains(Integer e) {
			return e>=a && e < b;
		}
		public Integer size() {
			return (b-a)/c;
		}
		public View1<IntRange,Integer> view1(){
			Integer n = this.size();
			Preconditions.checkArgument(n>0,String.format("La lista debe ser de tama�o mayor que 0 y es %d  ",n));
			return View1.of(this.a,IntRange.of(a+c,b));
		}
		public View2<IntRange,Integer> view2(){
			Integer n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tama�o mayor que 1 y es %d  ",n));
			Integer central = (b+a)/(2*c)*c;
			return View2.of(central,IntRange.of(a,central,c),IntRange.of(central,b,c));
		}
		public View2<IntRange,Integer> view2Overlapping(){
			Integer n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tama�o mayor que 1 y es %d  ",n));
			Integer central = (b+a)/(2*c)*c;
			return View2.of(central,IntRange.of(a,central+c,c),IntRange.of(central,b,c));
		}
		@Override
		public String toString() {
			String sc = c==1?"":("," + c);
			return "[" + a + "," + b + sc + ")";
		}	
	}
	
	
	public static class LongRange {
		public final Long a;
		public final Long b;
		public final Long c;
		public static LongRange of(Long a, Long b, Long c) {
			return new LongRange(a,b,c);
		}
		public static LongRange of(Long a, Long b) {
			return new LongRange(a,b,1L);
		}
		public LongRange(Long a, Long b, Long c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
		public Boolean isEmpty() {
			return a>=b;
		}
		public Boolean contains(Long e) {
			return e>=a && e < b;
		}
		public Long size() {
			return (b-a)/c;
		}
		public View1<LongRange,Long> view1(){
			Long n = this.size();
			Preconditions.checkArgument(n>0,String.format("La lista debe ser de tama�o mayor que 0 y es %d  ",n));
			return View1.of(this.a,LongRange.of(a+c,b));
		}
		public View2<LongRange,Long> view2(){
			Long n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tama�o mayor que 1 y es %d  ",n));
			Long central = (b+a)/(2*c)*c;
			return View2.of(central,LongRange.of(a,central,c),LongRange.of(central,b,c));
		}
		public View2<LongRange,Long> view2Overlapping(){
			Long n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tama�o mayor que 1 y es %d  ",n));
			Long central = (b+a)/(2*c)*c;
			return View2.of(central,LongRange.of(a,central+c,c),LongRange.of(central,b,c));
		}
		@Override
		public String toString() {
			String sc = c==1?"":("," + c);
			return "[" + a + "," + b + sc + ")";
		}
	}
	
	
	public static class DoubleRange {
		public final Double a;
		public final Double b;
		public final Double c;
		public static DoubleRange of(Double a, Double b, Double c) {
			return new DoubleRange(a,b,c);
		}
		public static DoubleRange of(Double a, Double b) {
			return new DoubleRange(a,b,1.);
		}
		DoubleRange(Double a, Double b, Double c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
		public Boolean isEmpty() {
			return a>=b;
		}
		public Boolean contains(Double e) {
			return e>=a && e < b;
		}
		public Integer size() {
			return (int) ((b-a)/c);
		}
		public View1<DoubleRange,Double> view1(){
			Integer n = this.size();
			Preconditions.checkArgument(n>0,String.format("La lista debe ser de tama�o mayor que 0 y es %d  ",n));
			return View1.of(this.a,DoubleRange.of(a+c,b));
		}
		public View2<DoubleRange,Double> view2(){
			Integer n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tama�o mayor que 1 y es %d  ",n));
			Double central = (b+a)/2;
			return View2.of(central,DoubleRange.of(a,central,c),DoubleRange.of(central,b,c));
		}
		public View2<DoubleRange,Double> view2Overlapping(){
			Integer n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tama�o mayor que 1 y es %d  ",n));
			Double central = (b+a)/2;
			return View2.of(central,DoubleRange.of(a,central+c,c),DoubleRange.of(central,b,c));
		}
		@Override
		public String toString() {
			String sc = c==1?"":("," + c);
			return "[" + a + "," + b + sc + ")";
		}
	}
	
	
	
}
