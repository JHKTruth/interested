# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
#
# Also note: You'll have to insert the output of 'django-admin sqlcustom [app_label]'
# into your database.
from __future__ import unicode_literals

from django.contrib.gis.db import models
from django.db import connection

"""
class DjangoMigrations(models.Model):
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class GeometryColumns(models.Model):
    f_table_catalog = models.CharField(db_column='F_TABLE_CATALOG', max_length=256, blank=True, null=True)  # Field name made lowercase.
    f_table_schema = models.CharField(db_column='F_TABLE_SCHEMA', max_length=256, blank=True, null=True)  # Field name made lowercase.
    f_table_name = models.CharField(db_column='F_TABLE_NAME', max_length=256)  # Field name made lowercase.
    f_geometry_column = models.CharField(db_column='F_GEOMETRY_COLUMN', max_length=256)  # Field name made lowercase.
    coord_dimension = models.IntegerField(db_column='COORD_DIMENSION', blank=True, null=True)  # Field name made lowercase.
    srid = models.IntegerField(db_column='SRID', blank=True, null=True)  # Field name made lowercase.
    type = models.CharField(db_column='TYPE', max_length=256)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'geometry_columns'


class Lines(models.Model):
    ogr_fid = models.AutoField(db_column='OGR_FID', primary_key=True)  # Field name made lowercase.
    shape = models.GeometryField(db_column='SHAPE')  # Field name made lowercase.
    osm_id = models.TextField(blank=True, null=True)
    name = models.TextField(blank=True, null=True)
    highway = models.TextField(blank=True, null=True)
    waterway = models.TextField(blank=True, null=True)
    aerialway = models.TextField(blank=True, null=True)
    barrier = models.TextField(blank=True, null=True)
    man_made = models.TextField(blank=True, null=True)
    other_tags = models.TextField(blank=True, null=True)
    objects = models.GeoManager()

    class Meta:
        managed = False
        db_table = 'lines'


class Multilinestrings(models.Model):
    ogr_fid = models.AutoField(db_column='OGR_FID', primary_key=True)  # Field name made lowercase.
    shape = models.GeometryField(db_column='SHAPE')  # Field name made lowercase.
    osm_id = models.TextField(blank=True, null=True)
    name = models.TextField(blank=True, null=True)
    type = models.TextField(blank=True, null=True)
    other_tags = models.TextField(blank=True, null=True)
    objects = models.GeoManager()

    class Meta:
        managed = False
        db_table = 'multilinestrings'


class Multipolygons(models.Model):
    ogr_fid = models.AutoField(db_column='OGR_FID', primary_key=True)  # Field name made lowercase.
    shape = models.GeometryField(db_column='SHAPE')  # Field name made lowercase.
    osm_id = models.TextField(blank=True, null=True)
    osm_way_id = models.TextField(blank=True, null=True)
    name = models.TextField(blank=True, null=True)
    type = models.TextField(blank=True, null=True)
    aeroway = models.TextField(blank=True, null=True)
    amenity = models.TextField(blank=True, null=True)
    admin_level = models.TextField(blank=True, null=True)
    barrier = models.TextField(blank=True, null=True)
    boundary = models.TextField(blank=True, null=True)
    building = models.TextField(blank=True, null=True)
    craft = models.TextField(blank=True, null=True)
    geological = models.TextField(blank=True, null=True)
    historic = models.TextField(blank=True, null=True)
    land_area = models.TextField(blank=True, null=True)
    landuse = models.TextField(blank=True, null=True)
    leisure = models.TextField(blank=True, null=True)
    man_made = models.TextField(blank=True, null=True)
    military = models.TextField(blank=True, null=True)
    natural = models.TextField(blank=True, null=True)
    office = models.TextField(blank=True, null=True)
    place = models.TextField(blank=True, null=True)
    shop = models.TextField(blank=True, null=True)
    sport = models.TextField(blank=True, null=True)
    tourism = models.TextField(blank=True, null=True)
    other_tags = models.TextField(blank=True, null=True)
    objects = models.GeoManager()

    class Meta:
        managed = False
        db_table = 'multipolygons'


class OtherRelations(models.Model):
    ogr_fid = models.AutoField(db_column='OGR_FID', primary_key=True)  # Field name made lowercase.
    shape = models.GeometryField(db_column='SHAPE')  # Field name made lowercase.
    osm_id = models.TextField(blank=True, null=True)
    name = models.TextField(blank=True, null=True)
    type = models.TextField(blank=True, null=True)
    other_tags = models.TextField(blank=True, null=True)
    objects = models.GeoManager()

    class Meta:
        managed = False
        db_table = 'other_relations'


class Points(models.Model):
    ogr_fid = models.AutoField(db_column='OGR_FID', primary_key=True)  # Field name made lowercase.
    shape = models.GeometryField(db_column='SHAPE')  # Field name made lowercase.
    osm_id = models.TextField(blank=True, null=True)
    name = models.TextField(blank=True, null=True)
    barrier = models.TextField(blank=True, null=True)
    highway = models.TextField(blank=True, null=True)
    ref = models.TextField(blank=True, null=True)
    address = models.TextField(blank=True, null=True)
    is_in = models.TextField(blank=True, null=True)
    place = models.TextField(blank=True, null=True)
    man_made = models.TextField(blank=True, null=True)
    other_tags = models.TextField(blank=True, null=True)
    objects = models.GeoManager()

    def pointsOfInterest(self, lng, lat, zipcode, tags, start=0, end=10):
        cursor = connection.cursor()
        cursor.execute("SELECT osm_id, name, "
            "round(st_distance_sphere(shape, st_geomfromtext('POINT (%f %f)', 1) ), 2) as dist"
            "FROM points"
            "WHERE st_within(shape,"
            "(select shape from tl_2013_us_zcta510 where zcta5ce10='%d') )"
            "and (other_tags like '%s')"
            "and name is not null"
            "ORDER BY dist asc LIMIT %d,%d;", [lng, lat, zipcode, tags, start, end])
        desc = cursor.description
        return [
            dict(zip([col[0] for col in desc], row))
            for row in cursor.fetchall()
        ]

    class Meta:
        managed = False
        db_table = 'points'


class SpatialRefSys(models.Model):
    srid = models.IntegerField(db_column='SRID')  # Field name made lowercase.
    auth_name = models.CharField(db_column='AUTH_NAME', max_length=256, blank=True, null=True)  # Field name made lowercase.
    auth_srid = models.IntegerField(db_column='AUTH_SRID', blank=True, null=True)  # Field name made lowercase.
    srtext = models.CharField(db_column='SRTEXT', max_length=2048, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'spatial_ref_sys'


class Tl2013UsZcta510(models.Model):
    ogr_fid = models.AutoField(db_column='OGR_FID', primary_key=True)  # Field name made lowercase.
    shape = models.GeometryField(db_column='SHAPE')  # Field name made lowercase.
    zcta5ce10 = models.CharField(max_length=5, blank=True, null=True)
    geoid10 = models.CharField(max_length=5, blank=True, null=True)
    classfp10 = models.CharField(max_length=2, blank=True, null=True)
    mtfcc10 = models.CharField(max_length=5, blank=True, null=True)
    funcstat10 = models.CharField(max_length=1, blank=True, null=True)
    aland10 = models.FloatField(blank=True, null=True)
    awater10 = models.FloatField(blank=True, null=True)
    intptlat10 = models.CharField(max_length=11, blank=True, null=True)
    intptlon10 = models.CharField(max_length=12, blank=True, null=True)
    objects = models.GeoManager()

    class Meta:
        managed = False
        db_table = 'tl_2013_us_zcta510'
"""
