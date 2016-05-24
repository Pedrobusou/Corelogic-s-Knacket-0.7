package uk.co.ribot.Knacket.data.local.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import uk.co.ribot.Knacket.data.model.Tag;

@DatabaseTable(tableName = "tag")
public class TagDatabase extends BaseTable{
    public static final String COLUMN_SERVER_ID = "server_id";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_DESCRIPTION = "description";
    private final String COLUMN_CREATED_AT = "created_at";
    private final String COLUMN_UPDATED_AT = "updated_at";
    private final String COLUMN_COUNTRY_ID = "country_id";

    @DatabaseField(columnName = COLUMN_SERVER_ID)
    String serverId;

    @DatabaseField(columnName = COLUMN_NAME)
    String name;

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    String description;

    @DatabaseField(columnName = COLUMN_CREATED_AT)
    String created_at;

    @DatabaseField(columnName = COLUMN_UPDATED_AT)
    String updated_at;

    @DatabaseField(columnName = COLUMN_COUNTRY_ID)
    String country_id;

    public TagDatabase() {}

    public TagDatabase(Tag tag) {
        this.name = tag.getName();
        this.description = tag.getDescription();
        this.created_at = tag.getCreated_at();
        this.updated_at = tag.getUpdated_at();
        this.serverId = tag.getId();
        this.country_id = tag.getCountry_id();
    }

    public TagDatabase(String name, String description, String created_at, String updated_at, String id, String country_id) {
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.serverId = id;
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String id) {
        this.serverId = id;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", id =" + id +
                '}';
    }
}