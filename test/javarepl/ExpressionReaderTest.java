package javarepl;

import org.junit.Test;

import static javarepl.ExpressionReader.lines;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExpressionReaderTest {

    @Test
    public void shouldTerminateCorrectlyWhenReadingTheExpression() {
        assertThat(new ExpressionReader(lines("int ", "x = 12;")).readExpression(), is("int"));
        assertThat(new ExpressionReader(lines("{", "int x = 12;")).readExpression(), is("{\nint x = 12;"));
        assertThat(new ExpressionReader(lines("{", "int x = 12;",")")).readExpression(), is("{\nint x = 12;\n)"));
        assertThat(new ExpressionReader(lines("{", "int x = 12;", "}")).readExpression(), is("{\nint x = 12;\n}"));
        assertThat(new ExpressionReader(lines("{", "int[]", "x = new int[]{12, 22};", "}")).readExpression(), is("{\nint[]\nx = new int[]{12, 22};\n}"));
        assertThat(new ExpressionReader(lines("{", "int", "", "", "}")).readExpression(), is("{\nint"));
    }


}