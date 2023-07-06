const AjaxAnimeTable = () => {
  const [items, setItems] = React.useState([]);
  const [error, setError] = React.useState(null);
  const [isLoading, setIsLoading] = React.useState(true);

  React.useEffect(() => {
    // ajax_alt takes three parameters: the URL to read, Success Fn, Failure Fn.
    ajax_alt(
      "anime/getAll",

      // success function (anonymous) 2nd param
      function (dbList) {
        // success function gets obj from ajax_alt
        if (dbList.dbError.length > 0) {
          setError(dbList.dbError);
        } else {
          console.log(dbList.animeList);
          setItems(dbList.animeList);
        }
        setIsLoading(false);
      },

      // failure function (also anonymous) 3rd param
      function (msg) {
        // failure message gets error message from ajax_alt
        setError(msg);
        setIsLoading(false);
      }
    );
  }, []);

  if (isLoading) {
    return (
      <div className="loading-animation">
        <span>
          Loading<span className="loading-animation-dot">.</span>
        </span>
      </div>
    );
  }

  return (
    <div>
      {error ? <div>Error: {error} </div> : <AnimeFilterTable list={items} />}
    </div>
  );
}; // class AjaxAnimeTable
