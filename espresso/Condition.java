import java.util.List;

class Condition {

    Condition(Column col1, String relation, Column col2) {
        _col1 = col1;
        _col2 = col2;
        _relation = relation;
    }

    Condition(Column col1, String relation, String val2) {
        this(col1, relation, (Column) null);
        _val2 = val2;
    }

    public boolean test(Integer... rows) {
        String value = _col1.getFrom(rows);
        int difference;
        if (_col2 == null) {
            difference = value.compareTo(_val2);
        } else {
            difference = value.compareTo(_col2.getFrom(rows));
        }
        switch (_relation) {
            case "=":
                return difference == 0;
            case ">":
                return difference > 0;
            case "<":
                return difference < 0;
            case ">=":
                return difference >= 0;
            case "<=":
                return difference <= 0;
            case "!=":
                return difference != 0;
            default:
                return false;
        }
    }

    public static boolean test(List<Condition> conditions, Integer... rows) {
        for (Condition cond : conditions) {
            if (!cond.test(rows)) {
                return false;
            }
        }
        return true;
    }

    /** The operands of this condition.  _col2 is null if the second operand
     *  is a literal. */
    private Column _col1, _col2;
    /** Second operand, if literal (otherwise null). */
    private String _val2;
    /** The relation of this condition between col1 and col2 or col1
     * and val2. */
    private String _relation;
}
