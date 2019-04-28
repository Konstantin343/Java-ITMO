package expression.generic;

import java.util.HashMap;
import java.util.Map;

import expression.TripleExpression;
import expression.exceptions.EvaluatingException;
import expression.exceptions.IncorrectModeException;	
import expression.parser.ExpressionParser;
import expression.parser.Parser;
import operations.*;

public class GenericTabulator implements Tabulator {
	
    private Map<String, Operation<?>> modes = new HashMap<>();
    {
        modes.put("i", new IntegerOperation("i"));
        modes.put("bi", new BigIntegerOperation());
        modes.put("d", new DoubleOperation());
        modes.put("u", new IntegerOperation("u"));
        modes.put("d", new DoubleOperation());
        modes.put("b", new ByteOperation());
        modes.put("f", new FloatOperation());
        modes.put("l", new LongOperation());
        modes.put("s", new ShortOperation());
    }
    
	private <T> Object[][][] table(Operation<T> op, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
		int x = x2 - x1 + 1;
		int y = y2 - y1 + 1;
		int z = z2 - z1 + 1;
		Object[][][] result = new Object[x][y][z];
		Parser<T> parser = new ExpressionParser<>(op);
		TripleExpression<T> exp = parser.parse(expression);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				for (int k = 0; k < z; k++) {		
					try {
						result[i][j][k] = exp.evaluate(op.intToType(x1 + i), op.intToType(y1 + j), op.intToType(z1 + k));
					} catch (EvaluatingException e) {
						result[i][j][k] = null;
						continue;
					}
				}
			}
		}
		return result;
	}
	
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
    	if (modes.get(mode) == null) {
    		throw new IncorrectModeException(mode);
    	}
    	return table(modes.get(mode), expression, x1, x2, y1, y2, z1, z2); 
    }
}
