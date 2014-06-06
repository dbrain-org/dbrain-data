package epic.data;

import epic.data.DataTruncationException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Static methods around Objects.
 */
public class Casts {

    private static final BigDecimal BD_LONG_MIN  = new BigDecimal( Long.MIN_VALUE );
    private static final BigDecimal BD_LONG_MAX  = new BigDecimal( Long.MAX_VALUE );
    private static final BigInteger BI_LONG_MIN  = new BigInteger( Long.toString( Long.MIN_VALUE ) );
    private static final BigInteger BI_LONG_MAX  = new BigInteger( Long.toString( Long.MAX_VALUE ) );
    private static final BigDecimal BD_INT_MIN   = new BigDecimal( Integer.MIN_VALUE );
    private static final BigDecimal BD_INT_MAX   = new BigDecimal( Integer.MAX_VALUE );
    private static final BigInteger BI_INT_MIN   = new BigInteger( Integer.toString( Integer.MIN_VALUE ) );
    private static final BigInteger BI_INT_MAX   = new BigInteger( Integer.toString( Integer.MAX_VALUE ) );
    private static final BigDecimal BD_SHORT_MIN = new BigDecimal( Short.MIN_VALUE );
    private static final BigDecimal BD_SHORT_MAX = new BigDecimal( Short.MAX_VALUE );
    private static final BigInteger BI_SHORT_MIN = new BigInteger( Short.toString( Short.MIN_VALUE ) );
    private static final BigInteger BI_SHORT_MAX = new BigInteger( Short.toString( Short.MAX_VALUE ) );
    private static final BigDecimal BD_BYTE_MIN  = new BigDecimal( Byte.MIN_VALUE );
    private static final BigDecimal BD_BYTE_MAX  = new BigDecimal( Byte.MAX_VALUE );
    private static final BigInteger BI_BYTE_MIN  = new BigInteger( Byte.toString( Byte.MIN_VALUE ) );
    private static final BigInteger BI_BYTE_MAX  = new BigInteger( Byte.toString( Byte.MAX_VALUE ) );

    /**
     * @param o
     * @param <T>
     * @return
     */
    public static <T> T identity( T o ) {
        return o;
    }

    /**
     * Cast object to BigDecimal.
     */
    public static BigDecimal toBigDecimal( Object o ) {
        if ( o == null ) return null;
        if ( o instanceof BigDecimal ) return (BigDecimal) o;
        if ( o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof Long || o instanceof AtomicInteger || o instanceof AtomicLong ) {
            return new BigDecimal( ( (Number) o ).longValue() );
        } else if ( o instanceof Float || o instanceof Double ) {
            return new BigDecimal( ( (Number) o ).doubleValue() );
        } else if ( o instanceof BigInteger ) {
            return new BigDecimal( (BigInteger) o );
        } else if ( o instanceof CharSequence ) {
            return new BigDecimal( o.toString().trim() );
        }
        throw new IllegalArgumentException( "Cannot cast " + o + " to BigDecimal." );
    }

    /**
     * Cast string to BigDecimal.
     */
    public static BigDecimal toBigDecimal( String o ) {
        if ( o == null ) return null;
        return new BigDecimal( o.trim() );
    }


    /**
     * Cast object to BigInteger.
     */
    public static BigInteger toBigInteger( Object o ) {
        if ( o == null ) return null;
        if ( o instanceof BigInteger ) return (BigInteger) o;
        if ( o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof Long || o instanceof AtomicInteger || o instanceof AtomicLong ) {
            return new BigInteger( o.toString() );
        }
        if ( o instanceof Float || o instanceof Double ) {
            return new BigDecimal( ( (Number) o ).doubleValue() ).toBigInteger();
        }
        if ( o instanceof BigDecimal ) {
            return ( (BigDecimal) o ).toBigInteger();
        }
        if ( o instanceof CharSequence ) {
            return new BigInteger( o.toString().trim() );
        }
        throw new IllegalArgumentException( "Cannot cast " + o + " to BigInteger." );
    }

    /**
     * Cast String to BigInteger.
     */
    public static BigInteger toBigInteger( String o ) {
        if ( o == null ) return null;
        return new BigInteger( o.trim() );
    }


    /**
     * Cast object to Double.
     */
    public static Double toDouble( Object o ) {
        if ( o == null ) return null;
        if ( o instanceof Double ) return (Double) o;
        if ( o instanceof Number ) {
            return ( (Number) o ).doubleValue();
        } else if ( o instanceof CharSequence ) {
            return Double.parseDouble( o.toString().trim() );
        }
        throw new IllegalArgumentException( "Cannot cast " + o + " to Double." );
    }

    /**
     * Cast object to Float.
     */
    public static Float toFloat( Object o ) {
        if ( o == null ) return null;
        if ( o instanceof Float ) return (Float) o;
        if ( o instanceof Number ) {
            return ( (Number) o ).floatValue();
        } else if ( o instanceof CharSequence ) {
            return Float.parseFloat( o.toString().trim() );
        }
        throw new IllegalArgumentException( "Cannot cast " + o + " to Float." );
    }

    /**
     * Cast object to long.
     */
    public static Long toLong( Object o ) {
        if ( o == null ) return null;
        if ( o instanceof Long ) return (Long) o;
        if ( o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof AtomicInteger || o instanceof AtomicLong ) {
            return ( (Number) o ).longValue();
        } else if ( o instanceof Float || o instanceof Double ) {
            double doubleValue = ( (Number) o ).doubleValue();
            if ( doubleValue >= Long.MIN_VALUE && doubleValue <= Long.MAX_VALUE ) {
                return (long) doubleValue;
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof BigDecimal ) {
            BigDecimal bigDecimal = (BigDecimal) o;
            if ( bigDecimal.compareTo( BD_LONG_MIN ) >= 0 && bigDecimal.compareTo( BD_LONG_MAX ) <= 0 ) {
                return bigDecimal.longValue();
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof BigInteger ) {
            BigInteger bigInteger = (BigInteger) o;
            if ( bigInteger.compareTo( BI_LONG_MIN ) >= 0 && bigInteger.compareTo( BI_LONG_MAX ) <= 0 ) {
                return bigInteger.longValue();
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof CharSequence ) {
            return Long.parseLong( o.toString().trim() );
        }
        throw new IllegalArgumentException( "Cannot cast " + o + " to Long." );
    }

    /**
     * Cast object to long.
     */
    public static Long toLong( String o ) {
        if ( o == null ) return null;
        return Long.parseLong( o.trim() );
    }

    /**
     * Cast object to String (null-safe).
     */
    public static String toString( Object o ) {
        return o != null ? o.toString() : null;
    }

    /**
     * Cast object to Boolean.
     */
    public static Boolean toBoolean( Object o ) {
        if ( o == null ) return null;
        if ( o instanceof Boolean ) return (Boolean) o;
        if ( o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof Long || o instanceof AtomicInteger || o instanceof AtomicLong ) {
            return ( (Number) o ).longValue() != 0;
        } else if ( o instanceof BigDecimal ) {
            return !o.equals( BigDecimal.ZERO );
        } else if ( o instanceof BigInteger ) {
            return !o.equals( BigInteger.ZERO );
        } else if ( o instanceof CharSequence ) {
            return Boolean.parseBoolean( o.toString().trim() );
        }
        throw new IllegalArgumentException( "Cannot cast " + o + " to Boolean." );
    }

    /**
     * Cast object to byte.
     */
    public static Byte toByte( Object o ) {
        if ( o == null ) return null;
        if ( o instanceof Byte ) return (Byte) o;
        if ( o instanceof Short || o instanceof Integer || o instanceof Long || o instanceof AtomicInteger || o instanceof AtomicLong ) {
            long longValue = ( (Number) o ).longValue();
            if ( longValue >= Byte.MIN_VALUE && longValue <= Byte.MAX_VALUE ) {
                return (byte) longValue;
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof Float || o instanceof Double ) {
            double doubleValue = ( (Number) o ).doubleValue();
            if ( doubleValue >= Byte.MIN_VALUE && doubleValue <= Byte.MAX_VALUE ) {
                return (byte) doubleValue;
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof BigDecimal ) {
            BigDecimal bigDecimal = (BigDecimal) o;
            if ( bigDecimal.compareTo( BD_BYTE_MIN ) >= 0 && bigDecimal.compareTo( BD_BYTE_MAX ) <= 0 ) {
                return bigDecimal.byteValue();
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof BigInteger ) {
            BigInteger bigInteger = (BigInteger) o;
            if ( bigInteger.compareTo( BI_BYTE_MIN ) >= 0 && bigInteger.compareTo( BI_BYTE_MAX ) <= 0 ) {
                return bigInteger.byteValue();
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof CharSequence ) {
            return Byte.parseByte( o.toString().trim() );
        }
        throw new IllegalArgumentException( "Cannot cast " + o + " to Byte." );
    }

    /**
     * Cast object to integer.
     */
    public static Integer toInteger( Object o ) {
        if ( o == null ) return null;
        if ( o instanceof Integer ) return (Integer) o;
        if ( o instanceof Byte || o instanceof Short || o instanceof AtomicInteger ) {
            return ( (Number) o ).intValue();
        } else if ( o instanceof Long || o instanceof AtomicLong ) {
            long longValue = ( (Number) o ).longValue();
            if ( longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE ) {
                return (int) longValue;
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof Float || o instanceof Double ) {
            double doubleValue = ( (Number) o ).doubleValue();
            if ( doubleValue >= Integer.MIN_VALUE && doubleValue <= Integer.MAX_VALUE ) {
                return (int) doubleValue;
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof BigDecimal ) {
            BigDecimal bigDecimal = (BigDecimal) o;
            if ( bigDecimal.compareTo( BD_INT_MIN ) >= 0 && bigDecimal.compareTo( BD_INT_MAX ) <= 0 ) {
                return bigDecimal.intValue();
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof BigInteger ) {
            BigInteger bigInteger = (BigInteger) o;
            if ( bigInteger.compareTo( BI_INT_MIN ) >= 0 && bigInteger.compareTo( BI_INT_MAX ) <= 0 ) {
                return bigInteger.intValue();
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof CharSequence ) {
            return Integer.parseInt( o.toString().trim() );
        }
        throw new IllegalArgumentException( "Cannot cast " + o + " to Integer." );
    }

    /**
     * Cast object to Short.
     */
    public static Short toShort( Object o ) {
        if ( o == null ) return null;
        if ( o instanceof Short ) return (Short) o;
        if ( o instanceof Byte || o instanceof Short ) {
            return ( (Number) o ).shortValue();
        } else if ( o instanceof Integer || o instanceof Long || o instanceof AtomicInteger || o instanceof AtomicLong ) {
            long longValue = ( (Number) o ).longValue();
            if ( longValue >= Short.MIN_VALUE && longValue <= Short.MAX_VALUE ) {
                return (short) longValue;
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof Float || o instanceof Double ) {
            double doubleValue = ( (Number) o ).doubleValue();
            if ( doubleValue >= Short.MIN_VALUE && doubleValue <= Short.MAX_VALUE ) {
                return (short) doubleValue;
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof BigDecimal ) {
            BigDecimal bigDecimal = (BigDecimal) o;
            if ( bigDecimal.compareTo( BD_SHORT_MIN ) >= 0 && bigDecimal.compareTo( BD_SHORT_MAX ) <= 0 ) {
                return bigDecimal.shortValue();
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof BigInteger ) {
            BigInteger bigInteger = (BigInteger) o;
            if ( bigInteger.compareTo( BI_SHORT_MIN ) >= 0 && bigInteger.compareTo( BI_SHORT_MAX ) <= 0 ) {
                return bigInteger.shortValue();
            } else {
                throw new DataTruncationException();
            }
        } else if ( o instanceof CharSequence ) {
            return Short.parseShort( o.toString().trim() );
        }
        throw new IllegalArgumentException( "Cannot cast " + o + " to Short." );
    }

    /**
     * Cast date to sql date.
     */
    public static java.sql.Date toSqlDate( java.util.Date date ) {
        return date != null ? new java.sql.Date( date.getTime() ) : null;
    }

    /**
     * Cast date to sql date.
     */
    public static java.sql.Date toSqlDate( LocalDate date ) {
        return date != null ? java.sql.Date.valueOf( date ) : null;
    }

    /**
     * Cast date to sql time.
     */
    public static java.sql.Time toSqlTime( java.util.Date date ) {
        return date != null ? new java.sql.Time( date.getTime() ) : null;
    }

    /**
     * Cast date to sql timestamp.
     */
    public static java.sql.Timestamp toSqlTimestamp( java.util.Date date ) {
        return date != null ? new java.sql.Timestamp( date.getTime() ) : null;
    }

}