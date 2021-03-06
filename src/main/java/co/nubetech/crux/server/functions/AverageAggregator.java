package co.nubetech.crux.server.functions;

import org.apache.hadoop.hbase.util.Bytes;

import co.nubetech.crux.util.CruxException;


public class AverageAggregator extends FunctionBase implements CruxAggregator{
	
	private int count;
	private double sum;
	
	public AverageAggregator(){
		count = 0;
		sum = 0;
	}
	
	/*
	 * this method is invoked for each value
	 */
	@Override
	public void aggregate(Object o) throws CruxException{
		if (o instanceof byte[]) {
			Double dbl = Bytes.toDouble((byte[]) o);
			count++;
			sum += dbl;
		}
		else {
			Double dbl = (Double) o;
			count++;
			sum += dbl;
		}
	}
	
	/*
	 * invoked in the end to get the result
	 */
	@Override
	public Object getAggregate() {
		if (count != 0) {
			return sum/count;
		}
		else {
			return null;
		}
	}
	
	@Override
	public boolean isAggregate() {
		return true;
	}
	

}
