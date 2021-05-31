package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    // iz db u app
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    // iz app u db
    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

}