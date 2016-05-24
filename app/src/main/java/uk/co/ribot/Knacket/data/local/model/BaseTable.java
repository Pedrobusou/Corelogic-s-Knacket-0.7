package uk.co.ribot.Knacket.data.local.model;

import com.j256.ormlite.field.DatabaseField;

public abstract class BaseTable {
    public static final String COLUMN_ID = "_id";

    @DatabaseField(generatedId = true, columnName = COLUMN_ID)
    long id;

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().isAssignableFrom(o.getClass())) return false;

        BaseTable baseTable = (BaseTable) o;

        return id == baseTable.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
