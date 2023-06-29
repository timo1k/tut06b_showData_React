const UserFilterTable = ({ list }) => {

    console.log("UserFilterTable running!!");

    // Tell React that 'isFirstRender' (boolean) is something that
    // (when changed) should redisplay this component.
    // Set its initial value to true.
    const [isFirstRender, setIsFirstRender] = React.useState(true);

    // on first rendering, build the user table directly from list (input param)
    // after the user clicks the search button, build the user table from 
    // the list that's been run through a filter operation. Otherwise, the 
    // list shows up empty intially.

    // Tell React that 'items' (array) is something that
    // (when changed) should redisplay this component.
    // Set items initial value to [], an empty array.
    const [showList, setShowList] = React.useState([]);
    console.log("Initial UserFilterTable value of list on next line");
    console.log(list);

    // Tell React that 'filterInput' is something that
    // (when changed) should cause this component to be 
    // redisplayed. Set initial value of filterInput to "".
    const [filterInput, setFilterInput] = React.useState("");

    const [isLoading, setIsLoading] = React.useState(true);

    const doFilter = (filterInputVal) => {
        let newList = filterObjList(list, filterInputVal);
        console.log("doFilter, filterInputVal is: " + filterInputVal + ". See filtered list on next line:");
        console.log(newList);
        setShowList(newList);
        setIsLoading(false);
    };

    // Do once. Passing "" as filterInput so all records should show initially.
    React.useEffect(
        () => { doFilter("") },
        []
    );

    if (isLoading) {
        return <div> Loading... </div>
    }

    return (
        <div className="clickSort">
            <h3>
                Web User List &nbsp;
                <input value={filterInput} onChange={(e) => setFilterInput(e.target.value)} />
                &nbsp; <button onClick={() => doFilter(filterInput)}>Search</button>
            </h3>

            <table>
                <thead>
                    <tr>
                        <th>Email</th>
                        <th className="textAlignCenter">Image</th>
                        <th className="textAlignCenter">Birthday</th>
                        <th className="textAlignRight">Membership Fee</th>
                        <th>Role</th>
                        <th>Error</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        showList.map(listObj =>
                            <tr key={listObj.webUserId}>
                                <td>{listObj.userEmail}</td>
                                <td className="shadowImage textAlignCenter"><img src={listObj.userImage} /></td>
                                <td className="textAlignCenter">{listObj.birthday}</td>
                                <td className="textAlignRight">{listObj.membershipFee}</td>
                                <td className="nowrap">{listObj.userRoleId} {listObj.userRoleType}</td>
                                <td>{listObj.errorMsg}</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    );
};