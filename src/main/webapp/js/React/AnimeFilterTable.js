const AnimeFilterTable = ({ list }) => {
  const [isFirstRender, setIsFirstRender] = React.useState(true);
  const [showList, setShowList] = React.useState([]);

  console.log(list);

  const [filterInput, setFilterInput] = React.useState("");
  const [isLoading, setIsLoading] = React.useState(true);

  const doFilter = (filterInputVal) => {
    let newList = filterObjList(list, filterInputVal);
    console.log(
      "doFilter, filterInputVal is: " +
        filterInputVal +
        ". See filtered list on next line:"
    );
    console.log(newList);
    setShowList(newList);
    setIsLoading(false);
  };

  // Do once. Passing "" as filterInput so all records should show initially.
  React.useEffect(() => {
    doFilter("");
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

  //   public String animeId = "";
  //   public String animeName = "";
  //   public String animeJapaneseName = "";
  //   public String animeImg = "";
  //   public String watchDate = "";
  //   public String animeRating = "";
  //   public String animeDesc = "";
  //   public String webUserId = "";
  //   public String userEmail = "";
  //   public String errorMsg = "";

  return (
    <div className="clickSort">
      <h3>
        Anime List &nbsp;
        <input
          value={filterInput}
          onChange={(e) => setFilterInput(e.target.value)}
        />
        &nbsp; <button onClick={() => doFilter(filterInput)}>Search</button>
      </h3>

      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>JAP Name</th>
            <th className="textAlignCenter">Image</th>
            <th className="textAlignCenter">WatchDate</th>
            <th className="textAlignRight">Rating</th>
            <th>WebUser</th>
            <th>Error</th>
          </tr>
        </thead>
        <tbody>
          {showList.map((listObj) => (
            <tr key={listObj.animeId}>
              <td>{listObj.animeName}</td>
              <td>{listObj.animeJapaneseName}</td>
              <td className="shadowImage textAlignCenter">
                <img src={listObj.animeImg} />
              </td>
              <td className="textAlignCenter">{listObj.watchDate}</td>
              <td className="textAlignRight">{listObj.animeRating}</td>
              <td className="nowrap">
                {listObj.webUserId} {listObj.userEmail}
              </td>
              <td>{listObj.errorMsg}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
