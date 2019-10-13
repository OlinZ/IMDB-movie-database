    select 
    min(id) as actor_id,
    ARRAY_AGG(connected_actors_id) as conected_actor_id,
    ARRAY_AGG(connecting_movie) as connecting_movie
    from(
        select 
        a1.nconst as id, 
        a2.nconst as connected_actors_id,
        array_to_string(array(select unnest(a1.knownfortitles_arr) INTERSECT select unnest(a2.knownfortitles_arr)),',') as connecting_movie
        from actors_list as a1
        join actors_list as a2 
        on a1.knownfortitles_arr && a2.knownfortitles_arr and 
        a1.nconst != a2.nconst
        where a1.nconst = 'nm0000001'
    ) as temp
    group by id;



    select 
    a1.nconst as id, 
    a2.nconst as connected_actors_id,
    array_to_string(array(select unnest(a1.knownfortitles_arr) INTERSECT select unnest(a2.knownfortitles_arr)),',') as connecting_movie
    from actors_list as a1
    join actors_list as a2 
    on a1.knownfortitles_arr && a2.knownfortitles_arr and 
    a1.nconst != a2.nconst
    where a1.nconst = 'nm0000001';