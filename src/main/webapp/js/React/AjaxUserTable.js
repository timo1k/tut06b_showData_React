const AjaxUserTable = () => {
  console.log("AjaxUserTable running");

  // Tell React that 'items' (an array of objects) is a state variable
  // that (when changed) should redisplay this component.
  // Set its initial value to [], an empty array.
  const [items, setItems] = React.useState([]);

  // Tell React that "error" is a state variable that (when changed)
  // should redisplay this component. Set its initial value to null.
  const [error, setError] = React.useState(null);

  const [isLoading, setIsLoading] = React.useState(true);

  // useEffect 2nd parameter is an array of elements that
  // (if any of those state variables change) should trigger the function specified
  // as the 1st useEffect parameter.
  // RUN ONCE PATTERN: If you put [] as 2nd param, it runs the 1st param (fn) once.
  React.useEffect(() => {
    // ajax_alt takes three parameters: the URL to read, Success Fn, Failure Fn.
    ajax_alt(
      "webUser/getAll",

      // success function (anonymous)
      function (dbList) {
        // success function gets obj from ajax_alt
        if (dbList.dbError.length > 0) {
          setError(dbList.dbError);
        } else {
          console.log("in AjaxUserTable here is web user list (next line):");
          console.log(dbList.webUserList);
          setItems(dbList.webUserList);
        }
        setIsLoading(false);
      },

      // failure function (also anonymous)
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
      {error ? <div>Error: {error} </div> : <UserFilterTable list={items} />}
    </div>
  );
}; // class AjaxUserTable
