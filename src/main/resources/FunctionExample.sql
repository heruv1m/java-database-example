CREATE ALIAS EXAMPLE_FUNC AS $$
String echo(String value) {
    return "echo_"+value;
}
$$;